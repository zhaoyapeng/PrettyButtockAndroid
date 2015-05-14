package com.lary.health.service.event;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-14下午10:12:37
 * @Email zhaoyp@witmob.com
 * @Description loading框event
 */
public class LoadingEvent implements IEvent {
	private boolean isShow;

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

}
