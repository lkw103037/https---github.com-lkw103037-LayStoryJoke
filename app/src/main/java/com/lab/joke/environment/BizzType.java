package com.lab.joke.environment;

/**
 * Created by rokevin on 15/10/2.
 * <p/>
 * 业务类型
 */
public class BizzType {

    /**
     * 发布状态
     */
    public class Publish {

        public static final String RECORD = "1";
        public static final String SPEECH = "2";
        public static final String TEXT = "3";
    }

    /**
     * 录音状态
     */
    public class Record {

        public static final String RECORD = "1";
        public static final String PAUSE = "2";
        public static final String CONTINUE_RECORD = "3";
    }
}
