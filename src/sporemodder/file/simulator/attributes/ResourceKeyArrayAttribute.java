/****************************************************************************
* Copyright (C) 2019 Eric Mor
*
* This file is part of SporeModder FX.
*
* SporeModder FX is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
****************************************************************************/
package sporemodder.file.simulator.attributes;

import java.io.IOException;

import sporemodder.file.filestructures.StreamReader;
import sporemodder.file.filestructures.StreamWriter;
import sporemodder.file.ResourceKey;

public class ResourceKeyArrayAttribute implements SimulatorAttribute {
	public ResourceKey[] value;
	
	@Override
	public void read(StreamReader stream, int size) throws IOException {
		value = new ResourceKey[stream.readInt()];
		for (int i = 0; i < value.length; i++) {
			ResourceKey key = new ResourceKey();
			value[i] = key;

			// Uses different order
			key.setGroupID(stream.readInt());
			key.setTypeID(stream.readInt());
			key.setInstanceID(stream.readInt());
		}
	}

	@Override
	public void write(StreamWriter stream) throws IOException {
		stream.writeInt(value.length);
		for (ResourceKey key : value) {
			// Uses different order
			stream.writeInt(key.getGroupID());
			stream.writeInt(key.getTypeID());
			stream.writeInt(key.getInstanceID());
		}
	}

	@Override
	public int getSize() {
		return 4 + 3 * 4 * value.length;
	}
	
	@Override
	public String toString(String tabulation) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < value.length; i++) {
			sb.append(value[i].toString());
			
			if (i + 1 < value.length) {
				sb.append(", ");
			}
		}
		sb.append(']');
		
		return sb.toString();
	}
	
	@Override
	public String toXmlString(String tabulation) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < value.length; i++) {
			sb.append(value[i].toString());
			
			if (i + 1 < value.length) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
}
