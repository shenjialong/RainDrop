package com.xiaoyudi.util;

public class GridViewSettings {

	public GridViewSettings(int columns,int rows,int layers){
		this.columns=columns;
		this.rows=rows;
		this.layers=layers;
	}
	
	
	public int columns;
	public int rows; 
	public int layers;
	public int columns_layer_up=4;
	public int rows_layer_up=2; 
	public int columns_layer_down=3;
	public int rows_layer_down=2; 
	
	public int getColumns_layer1_up() {
		return columns_layer_up;
	}
	public void setColumns_layer1_up(int columns_layer1_up) {
		this.columns_layer_up = columns_layer1_up;
	}
	public int getRows_layer1_up() {
		return rows_layer_up;
	}
	public void setRows_layer1_up(int rows_layer1_up) {
		this.rows_layer_up = rows_layer1_up;
	}
	public int getColumns_layer_down() {
		return columns_layer_down;
	}
	public void setColumns_layer_down(int columns_layer_down) {
		this.columns_layer_down = columns_layer_down;
	}
	public int getRows_layer_down() {
		return rows_layer_down;
	}
	public void setRows_layer_down(int rows_layer_down) {
		this.rows_layer_down = rows_layer_down;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	public int getRow() {
		return rows;
	}
	public void setRow(int row) {
		this.rows = row;
	}
	public int getLayers() {
		return layers;
	}
	public void setLayers(int layers) {
		this.layers = layers;
	}
	
	
	
}
