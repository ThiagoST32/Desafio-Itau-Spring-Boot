package com.desafio.itau.desafioItau.domain;

import lombok.Getter;

import java.util.DoubleSummaryStatistics;

/**
 * The Statistics class represents statistical metrics based on a collection
 * of numerical data. It calculates and stores count, sum, average, minimum,
 * and maximum values derived from the given data.
 * It utilizes a {@link DoubleSummaryStatistics} instance to retrieve
 * statistical information.
 * Fields in this class are immutable and represent the pre-calculated
 * statistical metrics.
 * Constructor:
 * - Accepts a {@link DoubleSummaryStatistics} object, from which it initializes
 *   the statistical metrics.
 */
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
