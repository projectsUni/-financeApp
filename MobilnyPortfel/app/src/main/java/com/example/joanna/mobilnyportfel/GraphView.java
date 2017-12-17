package com.example.joanna.mobilnyportfel;

/**
 * Created by Joanna on 2017-12-14.
 */

class GraphView implements GraphViewDataInterface {{

        private double x,y;

        public GraphViewData(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public double getX() {
            return this.x;
        }

        @Override
        public double getY() {
            return this.y;
        }

}
