package com.wth.cli.pattern;

/**
 * @Author: wth
 * @Create: 2024/2/27 - 21:16
 */
public class Device {

    private final String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(this.name + "打开");
    }

    public void turnOff() {
        System.out.println(this.name + "关闭");
    }
}
