package com.example.khaales.testkitchen;

/**
 * Created by khaales on 1/26/18.
 */

public class Food {

        private String date;
        private String quantity;
        private int present;

        public Food(){}
        public Food(String date, String quantity, int present) {
            this.date = date;
            this.quantity = quantity;
            this.present = present;
        }

        @Override
        public String toString() {
            return this.date + ": " + this.quantity + ": " + this.present;
        }

}
