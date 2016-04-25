package com.lab.joke.singleton;

public enum WWData {

    INSTANCE {

        private long mServerTime = 0;

        @Override
        public long getServerTime() {
            return mServerTime;
        }

        @Override
        public void setServerTime(long time) {

            mServerTime = time;
        }
    };

    public abstract long getServerTime();

    public abstract void setServerTime(long time);
}
