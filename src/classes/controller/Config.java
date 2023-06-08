package classes.controller;

public abstract class Config {
	public static boolean IS_TIMER_STOP = false;//定时器结束总开关，打开时停止所有定时器的刷新
	
    public final static int FRAME_WIDTH = 1200;//主窗体宽
    public final static int FRAME_HEIGHT = 900;//主窗体高
    public final static String IMAGE_RESOURCES_PATH = "src/resources/images/";//图片资源加载路径头
    public final static String ANIMATION_RESOURCES_PATH = "src/resources/animations/";//动画资源加载路径头
}
