package br.com.rafaelbiasi.chsmartbulbled.parameter;

import lombok.Getter;

import static java.lang.Math.floor;

@Getter
public enum SceneEffect {

	// 0x50 - Cor fixa: envia RGB, sem velocidade
	FIXED((byte) 0x50) {
		@Override
		public byte calculateSpeed(byte speed) {
			return 0x00;
		}

		@Override
		public Color color(Color color) {
			return sendColor(color);
		}
	},

	// 0x51 - KTV: não envia RGB; velocidade escala 0..30 -> byte alto
	KTV((byte) 0x51) {
		@Override
		public byte calculateSpeed(byte speed) {
			return scale30Descending(speed);
		}

		@Override
		public Color color(Color color) {
			return notSendColor();
		}
	},

	// 0x52 - Breathing: envia RGB; velocidade 0..15 com empacotamento especial
	BREATHING((byte) 0x52) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return sendColor(color);
		}
	},

	// 0x53 - Rainbow: não envia RGB; empacotamento especial
	RAINBOW((byte) 0x53) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return notSendColor();
		}
	},

	// 0x54 - Flash: envia RGB; empacotamento especial
	FLASH((byte) 0x54) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return sendColor(color);
		}
	},

	// 0x56 - Heartbeat: envia RGB; velocidade 0..15 no nibble alto (i << 4)
	HEARTBEAT((byte) 0x56) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packHighNibbleFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return sendColor(color);
		}
	},

	// 0x58 - Automatic: não envia RGB; empacotamento especial
	AUTOMATIC((byte) 0x58) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return notSendColor();
		}
	},

	// 0x5A - Candlelight: envia RGB; velocidade invertida 0..60
	CANDLELIGHT((byte) 0x5A) {
		@Override
		public byte calculateSpeed(byte speed) {
			return scale60Descending(speed);
		}

		@Override
		public Color color(Color color) {
			return sendColor(color);
		}
	},

	// 0x5C - Ocean: não envia RGB; empacotamento especial
	OCEAN((byte) 0x5C) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return notSendColor();
		}
	},

	// 0x5D - Natural: não envia RGB; empacotamento especial
	NATURAL((byte) 0x5D) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return notSendColor();
		}
	},

	// 0x5E - Sunset: não envia RGB; empacotamento especial
	SUNSET((byte) 0x5E) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return notSendColor();
		}
	},

	// 0x5F - Passion: não envia RGB; empacotamento especial
	PASSION((byte) 0x5F) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return notSendColor();
		}
	},

	// 0x61 - RGB-Cut: não envia RGB; empacotamento especial
	RGB_CUT((byte) 0x61) {
		@Override
		public byte calculateSpeed(byte speed) {
			return packNibblePatternFrom15(speed);
		}

		@Override
		public Color color(Color color) {
			return notSendColor();
		}
	};

	private final byte effect;

	SceneEffect(byte effect) {
		this.effect = effect;
	}

	public abstract byte calculateSpeed(byte speed);

	public abstract Color color(Color color);

	static void main() {
		IO.println(scale30Descending((byte) 30));
	}

	// Trata 'speed' como unsigned e mapeia para [0..15], invertido (15..0) como no app
	private static int speed15DescendingUnsigned(byte speed) {
		int s = speed & 0xFF;
		return 15 - (int) floor((s / 255.0) * 15.0);
	}

	// Mapeia para [0..30], invertido (30..0) — usado em KTV
	private static byte scale30Descending(byte speed) {
		int s = speed & 0xFF;
		int v = 30 - (int) floor((s / 255.0) * 30.0);
		return (byte) v;
	}

	// Mapeia para [0..60], invertido (60..0) — usado em Candlelight
	private static byte scale60Descending(byte speed) {
		int s = speed & 0xFF;
		int v = 60 - (int) floor((s / 255.0) * 60.0);
		return (byte) v;
	}

	// Empacotamento especial observado no app:
	// topByte = ((i >> 2) & 0x03) | (i << 4), onde i ∈ [0..15] (já invertido)
	private static byte packNibblePatternFrom15(byte speed) {
		int i = speed15DescendingUnsigned(speed) & 0x0F;
		int packed = ((i >> 2) & 0x03) | (i << 4);
		return (byte) (packed & 0xFF);
	}

	// Para HEARTBEAT: somente nibble alto ocupado (i << 4), i ∈ [0..15] (já invertido)
	private static byte packHighNibbleFrom15(byte speed) {
		int i = speed15DescendingUnsigned(speed) & 0x0F;
		return (byte) ((i << 4) & 0xF0);
	}

	Color notSendColor() {
		return Color.color().fullBlack();
	}

	Color sendColor(Color color) {
		return color;
	}
}