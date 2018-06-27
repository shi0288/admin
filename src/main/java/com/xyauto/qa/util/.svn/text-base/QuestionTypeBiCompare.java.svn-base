package com.xyauto.qa.util;



import com.xyauto.bi.entity.QuestionTypeBi;

import java.util.Comparator;

/**
 * Created by shiqm on 2017-05-23.
 */
public class QuestionTypeBiCompare implements Comparator<QuestionTypeBi>{

        @Override
        public int compare(QuestionTypeBi x, QuestionTypeBi y) {
            int a=x.getCreatedAt();
            int b=y.getCreatedAt();
            if(a<b){
                return -1;
            }else if(a>b){
                return 1;
            }
            return 0;
        }
}
