package com.wth.cli.pattern;

/**
 * @Author: wth
 * @Create: 2024/2/27 - 21:21
 */
public class Client {

    public static void main(String[] args) {

        Device tv = new Device("TV");
        Device camera = new Device("camera");

        Command tvTurnOffCommand = new TurnOffCommand(tv);
        Command tvTurnOnCommand = new TurnOnCommand(tv);
        Command cameraTurnOffCommand = new TurnOffCommand(camera);
        Command cameraTurnOnCommand = new TurnOnCommand(camera);

        RemoteCommand remoteCommand = new RemoteCommand();
        remoteCommand.setCommand(tvTurnOffCommand);
        remoteCommand.press();
        remoteCommand.setCommand(tvTurnOnCommand);
        remoteCommand.press();
        remoteCommand.setCommand(cameraTurnOnCommand);
        remoteCommand.press();
        remoteCommand.setCommand(cameraTurnOffCommand);
        remoteCommand.press();

    }
}
