package com.example.cg_lw1;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane graph;
    @FXML
    private TextField numberX;
    @FXML
    private TextField numberY;
    @FXML
    private TextField stepG;
    private LineChart<Number,Number> currentlyG;
    @FXML
    protected void buildGraph(){
        double numX=Double.parseDouble(numberX.getText());
        double numY=Double.parseDouble(numberY.getText());
        double inter=Double.parseDouble(stepG.getText());
        if(currentlyG!=null){
            graph.getChildren().remove(currentlyG);
        }
        currentlyG=getGraph(numX,numY,inter);
        currentlyG.setPrefHeight(graph.getHeight());
        currentlyG.setPrefWidth(graph.getWidth());
        graph.getChildren().addAll(currentlyG);
    }
    private LineChart<Number, Number> getGraph(double numX, double numY, double inter) {
        XYChart.Series series= new XYChart.Series();
        NumberAxis X=new NumberAxis(numX,numY,inter);
        NumberAxis Y=new NumberAxis();
        LineChart<Number,Number> lineChart= new LineChart<>(X,Y);
        lineChart.setTitle("Графік");
        series.setName("cos(x^2)/(x+1)");
        ObservableList<XYChart.Data> dataObservableList=FXCollections.observableArrayList();
        int step=getSteps(numX,numY,inter);
        for (int i=0;i<=step;i++){
            double curX=numX+i*inter;
            if (curX<=0)continue;
            double curY=fun(curX);
            dataObservableList.add(new XYChart.Data(curX,curY));
        }
        series.setData(dataObservableList);
        lineChart.getData().add(series);
        return lineChart;
    }
    private int getSteps(double numX, double numY, double inter) {
        return (int) ((numY-numX)*inter);
    }
    private double fun(double X) {
        return Math.cos(Math.pow(X,2))/(X+1);
    }
}