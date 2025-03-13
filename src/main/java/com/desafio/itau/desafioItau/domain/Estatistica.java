package com.desafio.itau.desafioItau.domain;

import java.util.DoubleSummaryStatistics;

public class Estatistica {

    private double count;
    private double sum;
    private double avg;
    private double min;
    private double max;


    public Estatistica(DoubleSummaryStatistics doubleSummaryStatistics){
        this.count = doubleSummaryStatistics.getCount();
        this.sum = doubleSummaryStatistics.getSum();
        this.avg = doubleSummaryStatistics.getAverage();
        this.min = doubleSummaryStatistics.getMin();
        this.max = doubleSummaryStatistics.getMax();
    }

    public double getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
