package com.wth.cli.pattern;

/**
 * @Author: wth
 * @Create: 2024/2/27 - 21:19
 */
public class RemoteCommand {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void press() {
        command.execute();
    }
}
