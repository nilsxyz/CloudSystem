package net.skillcloud.misc;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 24.08.2017, 19:12:17
 * @Created on 24.08.2017, 19:12:17
 */
public class Server {

    private final String uuid;
    private final String port;
    private final Process process;
    private final String name;

    public Server(String uuid, String port, Process process, String name) {
        this.uuid = uuid;
        this.port = port;
        this.process = process;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPort() {
        return port;
    }

    public Process getProcess() {
        return process;
    }

    public String getName() {
        return name;
    }

}
