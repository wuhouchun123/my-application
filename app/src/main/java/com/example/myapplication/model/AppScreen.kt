package com.example.myapplication.model

/**
 * title can also come from StringRes
 *
 * enum class Screen(@StringRes title: Int) { ... }
 */
enum class AppScreen(val title: String) {
    Home(title = "壮兔dorunto的空间"), // start screen
    LikeAnimation(title = "点赞的数字点击动画"),
    DraggableMusicKnob(title = "可调节音量的音乐组件"),
    DraggableBall(title = "可移动吸附的小球"),
    Timer(title = "计时器仪表盘"),
    BottomNavigation(title = "底部菜单栏"),
    Dialog(title = "点击显示弹窗"),
    Drawer(title = "侧边栏"),
    Chat(title = "聊天功能"),
    FixedBall(title = "滑动到某位置固定的球")
}
