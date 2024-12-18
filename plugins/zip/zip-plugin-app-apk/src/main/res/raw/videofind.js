// ==UserScript==
// @name         VideoFind 2
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @match        *://*.com/*
// @match        *://*.net/*
// @match        *://*.cn/*
// @grant        none
// ==/UserScript==

//(function() {
//    'use strict';

var log = function(message) {
    window.android.logger(message);
    console.log(message);
};

var play = function(title, src) {
    window.android.playVideo(title, src);
};

var clickplaybtn = function(name) {
    window.android.clickplaybtn(name);
};

function isGuanGao(duration){
    if(duration < 50){
        return true;
    }
    return false;
};

function setupVideoHandler() {
    var videos = document.getElementsByTagName('video');
//    log('video 标签个数 ' + videos.length);
    for (var i = 0; i < videos.length; i++) {
        if (videos[i].getAttribute('haveInject') == null) {
            videos[i].setAttribute('x-webkit-airplay', 'false');
            videos[i].setAttribute('webkit-playsinline', 'false');
            videos[i].setAttribute('playsinline', 'false');

            log('weburl: ' + window.location.href);
            var yumin = window.location.href.split('/')[2];
            if(yumin.match("/qq.com/i") == null||yumin.match("youtube.com") == null){
                //腾讯视频添加这个属性会造成进入视频详解页面会自动播放
                //爱奇艺不添加这个属性可能会造成播放界面返回后会再次自动播放
                log('设置不能自动播放和循环');
//                videos[i].setAttribute('loop', 'false');
                videos[i].setAttribute('autoplay', 'false');
            }

            videos[i].addEventListener('durationchange', function() {
                var mDuration = this.duration;
                var mSrc = this.getAttribute('src');
//                log('js:durationchange:duration:' + mDuration);
//                log('js:durationchange:src:' + mSrc);
            });

             videos[i].addEventListener('play', function() {
                 log('js:play========================' );
             });

            videos[i].onplaying = function() {
                var mDuration = this.duration;
//                log('js:onplaying:duration:' + mDuration);

                var mSrc = null;
                try {
                    if (mSrc == null) {
                        mSrc = this.getAttribute('src');
                        if (mSrc!= null && mSrc.startsWith('//')) { mSrc = 'http:' + mSrc; }
                        log('js:onplay:src1:' + mSrc);
                    }
                    if (mSrc == null) {
                        mSrc = this.getElementsByTagName('source')[0].getAttribute('src');
                        if (mSrc.startsWith('//')) { mSrc = 'http:' + mSrc; }
                        log('js:onplay:src2:' + mSrc);
                    }
                }
                catch (error) {
                    log('js:onplay:err:' + error);
                }
                var mIsVideo = false;
                var isAiQiYi = false;
                var isYoutube = false;
                if (mSrc != null) {
                    var mDomain = window.location.href.split('/')[2];
                    log('mDomain: ' + mDomain);
                    if (mDomain.match("/bilibili.com/i") != null) {
                        log('js:哔哩哔哩视频');
                        mIsVideo = true;
                    }
                    else if (mDomain.match("/youku.com/i") != null) {
                        log('js:优酷视频');
                        mIsVideo = true;
                    }
                    else if (mDomain.match("/qq.com/i") != null) {
//                        if (mSrc.match(/tc.qq.com/i) == null) {
                            log('js:腾讯视频');
//                            mIsVideo = true;
//                        }
                        if(isGuanGao(mDuration)){
                            mIsVideo = false;
                        }else{
                            mIsVideo = true;
                        }
                        
                    }
                    //else if (mDomain.match(/iqiyi.com/i) != null) {
                    else if (mDomain.match("iqiyi.") != null) {
                       // if (mSrc.match(/random=ad-/i) == null) {
                              log('js:爱奇艺视频');
                              mIsVideo = true;
                              isAiQiYi = true;
                       // }
                    } else if (mDomain.match("youtube.com") != null) {
                         log('js:Youtube视频');
                         isYoutube=true;
                         mIsVideo = true;
                    }
                    else {
                        log('js:其他视频');
                        mIsVideo = true;
                    }
                    if (mIsVideo) {
                    log('>>>>>>>>>>>>>>>>> 跳转播放 ' + mSrc);
                        if(isAiQiYi){
                             document.getElementsByTagName('video')[0].pause();
                             play(document.title, mSrc);
                             document.getElementsByTagName('video')[0].onplaying = null;
                             setTimeout(function(){document.getElementsByTagName('video')[0].removeAttribute('haveInject')} , 200);
                            //document.getElementsByTagName('video')[0].removeAttribute('haveInject');
                        }else if(isYoutube){
                            this.pause();
                            mSrc = ytInitialPlayerResponse.streamingData.formats[0].url;
                            log('youtube url='+mSrc);
                            play(ytInitialPlayerResponse.videoDetails.title, mSrc);
//                            clickplaybtn('');
                            this.ended();
                        }else{
                        log('>>>>>>not isAiQiYi>>>>>>>>>>>  ' );
                            this.pause();
                            play(document.title, mSrc);
                            this.ended();
                        }

                    }
                }
            };
            videos[i].setAttribute('haveInject', 'true');
        }
    }
}

function setupAHandler() {
    var a = document.getElementsByTagName('a');
    for (var i = 0; i < a.length; i++) {
        if (a.item(i).getAttribute('haveInject') == null) {
            a.item(i).onclick = function() {
                if (this.target == '_blank') {
                    this.target = '';
                }
                if (this.href.match("/app/i") != null) {
                    this.value = '';
                    this.href = 'javascript:void(0)';
                }
                if (this.value.match("/app/i") != null) {
                    this.value = '';
                    this.href = 'javascript:void(0)';
                }
            };
            a.item(i).setAttribute('haveInject', 'true');
        }
    }
}

function setupVidePlayingListener() {
    setupVideoHandler();

    setupAHandler();

    // Otherwise, wait for 100ms and check again.
    setTimeout(setupVidePlayingListener, 100);
}

setupVidePlayingListener();

//})();
