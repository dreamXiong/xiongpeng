package com.linkon.admin.custom.constant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public enum PeriodCondition {
    LATEST_0_DAY(0, "0天", 1) {
        @Override
        protected int getPeriodValue() {

            return 1;
        }
    },
    LATEST_3_DAY(1, "3天", 1) {
        @Override
        protected int getPeriodValue() {

            return 3;
        }
    },
    LATEST_5_DAY(2, "5天", 1) {
        @Override
        protected int getPeriodValue() {

            return 5;
        }
    };

    // --------------------------------------------------------------------------------------------
    // Instance fields
    // --------------------------------------------------------------------------------------------

    private int period;
    private String label;
    private PeriodDate periodDate;
    private int periodType;

    // --------------------------------------------------------------------------------------------
    // Instance methods
    // --------------------------------------------------------------------------------------------

    /**
     * 
     * @param period
     * @param label
     * @param periodType: 1:day,2:month
     */
    private PeriodCondition(int period, String label, int periodType) {

        this.period = period;
        this.label = label;
        this.periodType = periodType;
        this.calculatePeriodDate();

    }

    /* ----------------------------------------------------------------------------------------- */

    public PeriodDate getPeriodDate() {

        return this.periodDate;
    }

    /* ----------------------------------------------------------------------------------------- */

    public static PeriodCondition getConditionByPeriod(int period) {

        for (PeriodCondition condition : PeriodCondition.values()) {
            if (period == condition.period) {
                return condition;
            }
        }
        return null;
    }

    /* ----------------------------------------------------------------------------------------- */

    protected abstract int getPeriodValue();

    /* ----------------------------------------------------------------------------------------- */

    public void calculatePeriodDate() {

        this.periodDate = new PeriodDate();
        int periodValue = this.getPeriodValue();
        long currentTime = System.currentTimeMillis();

        if (this.periodType == 1) {
            this.periodDate.setStartDate(currentTime);
            this.periodDate.setEndDate(currentTime + periodValue * 24 * 60 * 60 * 1000);
        } else if (this.periodType == 2) {
            this.periodDate.setStartDate(currentTime);

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal2.set(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + periodValue, 1);
            if (cal1.get(Calendar.DAY_OF_MONTH) > cal2.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                cal2.set(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + periodValue, cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                cal2.set(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH) + periodValue, cal1.get(Calendar.DAY_OF_MONTH));
            }
            this.periodDate.setEndDate(cal2.getTimeInMillis());
            // System.out.println("..." + this.periodType + "-" + this.label + "-" + this.period);
        }
    }

    /* public static void main(String[] args) {

         int value = 101;
         PeriodCondition p = PeriodCondition.getConditionByPeriod(value);

         System.out.println("..." + p.periodType + "-" + p.label + "-" + p.getPeriodValue() + ":" + p.periodDate.getStartDate() + "---" + p.periodDate.getEndDate());

     }*/

    /* ----------------------------------------------------------------------------------------- */
}
