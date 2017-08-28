package net.skillcloud.misc;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 27.08.2017, 12:01:40
 * @Created on 27.08.2017, 12:01:40
 */
public class MinecraftServer {

    private final int memory;
    private final int maxmemory;
    private final int size;
    private final String jar;
    private final String port;
    private final String exists;
    private final String path;
    private final String name;

    public MinecraftServer(int memory, int maxmemory, int size, String jar, String port, String exists, String path, String name) {
        this.memory = memory;
        this.maxmemory = maxmemory;
        this.size = size;
        this.jar = jar;
        this.port = port;
        this.exists = exists;
        this.path = path;
        this.name = name;
    }

    public int getMemory() {
        return memory;
    }

    public int getMaxmemory() {
        return maxmemory;
    }

    public int getSize() {
        return size;
    }

    public String getJar() {
        return jar;
    }

    public String getPort() {
        return port;
    }

    public String getExists() {
        return exists;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

}
