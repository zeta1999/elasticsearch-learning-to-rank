/*
 * Copyright [2017] Wikimedia Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.o19s.es.ltr.ranker.linear;

import com.o19s.es.ltr.ranker.ArrayDataPoint;
import com.o19s.es.ltr.ranker.LtrRanker;

/**
 * Simple linear ranker that applies a dot product based
 * on the provided weights array.
 */
public class LinearRanker implements LtrRanker {
    private final float[] weights;

    public LinearRanker(float weight[]) {
        this.weights = weight;
    }

    @Override
    public String name() {
        return "linear";
    }

    @Override
    public DataPoint newDataPoint() {
        return new ArrayDataPoint(size());
    }

    @Override
    public float score(DataPoint point) {
        assert point instanceof ArrayDataPoint;
        float[] scores = ((ArrayDataPoint) point).scores;
        float score = 0;
        for (int i = 0; i < weights.length; i++) {
            score += weights[i]*scores[i];
        }
        return score;
    }

    @Override
    public int size() {
        return weights.length;
    }
}
