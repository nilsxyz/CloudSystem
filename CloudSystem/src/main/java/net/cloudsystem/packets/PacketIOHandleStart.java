package net.cloudsystem.packets;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.cloudsystem.CloudSystem;
import net.cloudsystem.abstractes.Packet;
import net.cloudsystem.managers.CloudManager;
import net.skillcloud.misc.MinecraftServer;
import net.skillcloud.misc.Server;
/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 27.08.2017, 19:40:20
 * @Created on 27.08.2017, 19:40:20
 */
public class PacketIOHandleStart extends Packet {

    private final MinecraftServer minecraftServer;

    public PacketIOHandleStart(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
    }

    @Override
    public void execute() {
        try {
            try {
                if (minecraftServer.getSize() == 1) {
                    ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
                    String[] array = {"java", "-Xms" + minecraftServer.getMemory() + "M", "-Xmx" + minecraftServer.getMaxmemory() + "M", "-jar", minecraftServer.getJar() + ".jar", "-p", minecraftServer.getPort()};
                    processBuilder.command(array);
                    processBuilder.directory(new File(minecraftServer.getPath()));
                    Process process = processBuilder.start();
                    System.out.println("Die " + minecraftServer.getName() + " wurde gestartet");
                    CloudSystem.processes.put(minecraftServer.getName(), new Server(UUID.randomUUID().toString(), minecraftServer.getPort(), process, minecraftServer.getName()));
                } else {
                    for (int i = 1; i != minecraftServer.getSize(); i++) {
                        ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
                        String[] array = {"java", "-Xms" + minecraftServer.getMemory() + "M", "-Xmx" + minecraftServer.getMaxmemory() + "M", "-jar", minecraftServer.getJar() + ".jar", "-p", minecraftServer.getPort().replaceAll("%i%", String.valueOf(i))};
                        processBuilder.command(array);
                        processBuilder.directory(new File(minecraftServer.getPath().replaceAll("%i%", String.valueOf(i))));
                        Process process = processBuilder.start();
                        System.out.println("Die " + minecraftServer.getName() + "-" + i + " wurde gestartet");
                        CloudSystem.processes.put(minecraftServer.getName() + "-" + i, new Server(UUID.randomUUID().toString(), minecraftServer.getPort().replaceAll("%i%", String.valueOf(i)), process, minecraftServer.getName() + "-" + i));
                    }
                }
            } catch (NumberFormatException ex) {
                Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(CloudManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
