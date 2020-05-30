package Game_of_Life;

import javax.swing.Spring;

public class Stat {
	private int[] statistics = {0,0,0,0,0,0,0,0};
	private int[] best_statistics = {0,0,0,0,0,0,0,0};
	Stat() {
		for(int i = 0; i < 8; i ++) {
		statistics[i] = 0;
		best_statistics[i] = 0;
		}
	}
	public void set_statistics(int[] arr) {
		statistics = arr;
	}
	public void set_best_statistics(int[] arr) {
		best_statistics = arr;
	}
	public int[] get_statistics_array() {
		return statistics;
	}
	public int[] get_best_statistics_array() {
		return best_statistics;
	}
	
	public void set_whites_and_blacks(int[] wb) {
		statistics[1] = wb[0];
		statistics[2] = wb[1];
	}
	

	public void clear_statistics() {
		for(int i = 0; i < 8; i ++)
			statistics[i] = 0;
	}
	public void plus_step() {
		statistics[0]++;
		System.out.println(get_statistics());
	}
	public void plus_whites() {
		statistics[0]++;
	}
	public void plus_blacks() {
		statistics[0]++;
	}
	public void plus_dots() {
		statistics[0]++;
		statistics[3]++;
	}
	public void plus_glyders() {
		statistics[0]++;
		statistics[4]++;
	}
	public void plus_switches() {
		statistics[0]++;
		statistics[5]++;
	}
	public void plus_spaceships() {
		statistics[0]++;
		statistics[6]++;
	}
	public void plus_pulsars() {
		statistics[0]++;
		statistics[7]++;
	}
	public String get_statistics() {
		return ("Steps: " + statistics[0] + " Whites: " + statistics[1] + " Blacks: "+ statistics[2] 
				+ " Dots: " + statistics[3] + " Glyders: " + statistics[4] + " Switches: " + statistics[5] + 
				" Spaceships: "+ statistics[6] + " Pulsars: " + statistics[7]); 
	}
	
	public String get_best_statistics() {
		return ("BEST: Steps: " + best_statistics[0] + " Whites: " + best_statistics[1] + " Blacks: "+ best_statistics[2] 
				+ " Dots: " + best_statistics[3] + " Glyders: " + best_statistics[4] + " Switches: " + best_statistics[5] + 
				" Spaceships: "+ best_statistics[6] + " Pulsars: " + best_statistics[7]); 
	}
}
