package br.com.rafaelbiasi.chsmartbulbled.parameter;

import lombok.Getter;

@Getter
public enum SceneColor {
	
	READING(Color.color().white(165).yellowIntensity(90)),
	WORKING(Color.color().white(90).yellowIntensity(165)),
	RECEIVE(Color.color().rgb(0, 0, 128)),
	CAMPING(Color.color().rgb(225, 105, 180)),
	COFFEE(Color.color().rgb(34, 139, 34)),
	DINING(Color.color().rgb(220, 20, 60)),
	SLEEP(Color.color().rgb(112, 128, 144)),
	CINEMA(Color.color().white(25).yellowIntensity(25)),
	SUMMER(Color.color().rgb(0, 255, 0)),
	NIGHT(Color.color().rgb(255, 255, 0)),
	NETWORK(Color.color().rgb(139, 0, 139)),
	BUS(Color.color().rgb(85, 107, 47));

	private final Color color;

	SceneColor(Color color) {
		this.color = color;
	}
}
