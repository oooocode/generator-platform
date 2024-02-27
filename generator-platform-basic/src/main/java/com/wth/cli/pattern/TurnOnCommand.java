package com.wth.cli.pattern;

/**
 * @Author: wth
 * @Create: 2024/2/27 - 21:16
 */
public class TurnOnCommand implements Command {

    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOn();
    }

}
