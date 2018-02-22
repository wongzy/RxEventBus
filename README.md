### RxEventBus | [中文文档](https://github.com/JoshuaRogue/RxEventBus/blob/master/CREADME.md)

A EventBus based on RxJava2, using Retention.CLASS annotation.

#### Getting Started

* Subscriber

1. register subscriber

```
@Override
    public void onStart() {
        super.onStart();
        RxEventBus.getDefault().register(this);
    }
```

2. write Subscribe method, like this 

```
@Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageHandle(String s) {
        mTextView.setText(s);
    }
```

3. don't forget unregister when activity or others destroyed

```
@Override
    public void onDestroy() {
        super.onDestroy();
        RxEventBus.getDefault().unregister(this);
    }
```

* Post

RxEventBus.getDefault().post(mEditText.getText().toString());

> Of cause you can post any kind of message, not just String

#### Install

this project isn't pushed to maven, if you want to use it, just 

```
git clone https://github.com/JoshuaRogue/RxEventBus.git
```

and copy module to your project.

It will be pushed to maven soon.

#### demo

<img width="293" height="520" src="https://i.loli.net/2018/02/22/5a8e9f930b985.gif"/>