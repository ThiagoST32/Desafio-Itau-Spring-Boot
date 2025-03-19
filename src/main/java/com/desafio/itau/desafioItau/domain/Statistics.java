package com.desafio.itau.desafioItau.domain;

import lombok.Getter;

import java.util.DoubleSummaryStatistics;

@Getter
public class Statistics {

    private final double count;
    private final double sum;
    private final double avg;
    private final double min;
    private final double max;


    public Statistics(DoubleSummaryStatistics doubleSummaryStatistics){
        this.count = doubleSummaryStatistics.getCount();
        this.sum = doubleSummaryStatistics.getSum();
        this.avg = doubleSummaryStatistics.getAverage();
        this.min = doubleSummaryStatistics.getMin();
        this.max = doubleSummaryStatistics.getMax();
    }
}
