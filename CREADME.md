### RxEventBus 

使用RxJava2和编译时注解实现的EventBus

#### 开始

* 观察者

1. 注册观察者

```
@Override
    public void onStart() {
        super.onStart();
        RxEventBus.getDefault().register(this);
    }
```

2. 编写对发来信息进行处理的函数

```
@Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageHandle(String s) {
        mTextView.setText(s);
    }
```

3. 最后，别忘了反注册

```
@Override
    public void onDestroy() {
        super.onDestroy();
        RxEventBus.getDefault().unregister(this);
    }
```

* 发送消息

RxEventBus.getDefault().post(mEditText.getText().toString());

> 你可以发送任何类型的消息

#### 安装

这个项目还没有被上传到maven仓库，如果你想要使用，可以

```
git clone https://github.com/JoshuaRogue/RxEventBus.git
```

然后将模块拷贝到自己的项目中

这个项目会尽快上传到maven仓库以方便大家的使用

#### 示例

<img width="293" height="520" src="https://i.loli.net/2018/02/22/5a8e9f930b985.gif"/>