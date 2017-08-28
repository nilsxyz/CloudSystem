package net.cloudsystem.managers;

import net.cloudsystem.abstractes.Packet;
import net.cloudsystem.misc.PacketSender;
import net.cloudsystem.packets.PacketIOHandleStart;
import net.cloudsystem.packets.PacketIOHandleStop;
import net.cloudsystem.packets.PacketOutHandleTmpFile;
import net.skillcloud.misc.MinecraftServer;


/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 27.08.2017, 20:29:47
 * @Created on 27.08.2017, 20:29:47
 */
public class PacketManager {

    private PacketSender sender;

    public Packet startServer(MinecraftServer minecraftServer) {
        PacketIOHandleStart packet = new PacketIOHandleStart(minecraftServer);
        sender.sendPacket(packet);
        return packet;
    }

    public Packet startServer(String name) {
        PacketIOHandleStop packet = new PacketIOHandleStop(name);
        sender.sendPacket(packet);
        return packet;
    }

    public Packet copyTmp(String path, String notfile, String directory, int size) {
        PacketOutHandleTmpFile packet = new PacketOutHandleTmpFile(path, notfile, directory, 0);
        sender.sendPacket(packet);
        return packet;
    }

}
