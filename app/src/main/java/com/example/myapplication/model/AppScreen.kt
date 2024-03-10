package com.example.myapplication.model

/**
 * title can also come from StringRes
 *
 * enum class Screen(@StringRes title: Int) { ... }
 */
enum class AppScreen(val title: String) {
    Home(title = "我的Compose之旅"), // start screen
    LikeAnimation(title = "点赞的数字点击动画"),
    DraggableMusicKnob(title = "可调节音量的音乐组件"),
    DraggableMusicKnob2(title = "可调节音量的音乐组件(再写一遍)"),
    DraggableBall(title = "可移动吸附的小球"),
    Timer(title = "计时器仪表盘"),
    Timer2(title = "计时器仪表盘(再写一遍)"),
    BottomNavigation(title = "底部菜单栏"),
    Dialog(title = "点击显示弹窗"),
    Drawer(title = "侧边栏"),
    Chat(title = "聊天功能"),
    FixedBall(title = "滑动到某位置固定的球"),
    CircularProgressBar(title = "动态圆弧进度条"),
    Animated3DDropDown(title = "3D下拉动画"),
    Navigate("导航到某个页面"),
    SplashScreen("载入页动画"),
    BottomNavigationWithBadges("带气泡的底部导航栏"),
    MultiSelectLazyColumn("多选LazyColumn"),
    PermissionHandling("应用权限处理"),
    ThemeAddSpacing("主题添加间距"),
    SupportAllScreen("支持所有屏幕")
}
