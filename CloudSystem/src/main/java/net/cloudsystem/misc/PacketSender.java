package net.cloudsystem.misc;

import net.cloudsystem.abstractes.Packet;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 27.08.2017, 19:51:31
 * @Created on 27.08.2017, 19:51:31
 */

public class PacketSender {
    
    public void sendPacket(Packet packet) {
        packet.execute();
    }

}
