package com.udasboot.witheringends.core.network.message;

import net.minecraft.network.PacketBuffer;

public class InputMessage {
	
	public int breath;
	
	public InputMessage() {

	}

	public InputMessage(int breath) {
		this.breath = breath;
	}
	
	public static void encode(InputMessage message, PacketBuffer buffer) {
		buffer.writeInt(message.breath);
	}
}
