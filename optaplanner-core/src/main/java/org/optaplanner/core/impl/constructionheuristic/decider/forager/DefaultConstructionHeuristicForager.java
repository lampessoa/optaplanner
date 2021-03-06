/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.core.impl.constructionheuristic.decider.forager;

import java.util.Comparator;

import org.optaplanner.core.api.score.FeasibilityScore;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.comparator.NaturalScoreComparator;
import org.optaplanner.core.config.constructionheuristic.decider.forager.ConstructionHeuristicPickEarlyType;
import org.optaplanner.core.impl.constructionheuristic.scope.ConstructionHeuristicMoveScope;
import org.optaplanner.core.impl.constructionheuristic.scope.ConstructionHeuristicStepScope;

public class DefaultConstructionHeuristicForager extends AbstractConstructionHeuristicForager {

    protected final ConstructionHeuristicPickEarlyType pickEarlyType;

    protected Comparator<Score> scoreComparator;

    protected ConstructionHeuristicMoveScope earlyPickedMoveScope;
    protected ConstructionHeuristicMoveScope maxScoreMoveScope;

    public DefaultConstructionHeuristicForager(ConstructionHeuristicPickEarlyType pickEarlyType) {
        this.pickEarlyType = pickEarlyType;
        scoreComparator = new NaturalScoreComparator();
    }

    // ************************************************************************
    // Worker methods
    // ************************************************************************

    @Override
    public void stepStarted(ConstructionHeuristicStepScope stepScope) {
        super.stepStarted(stepScope);
        earlyPickedMoveScope = null;
        maxScoreMoveScope = null;
    }

    @Override
    public void stepEnded(ConstructionHeuristicStepScope stepScope) {
        super.stepEnded(stepScope);
        earlyPickedMoveScope = null;
        maxScoreMoveScope = null;
    }

    public void addMove(ConstructionHeuristicMoveScope moveScope) {
        checkPickEarly(moveScope);
        if (maxScoreMoveScope == null
                || scoreComparator.compare(moveScope.getScore(), maxScoreMoveScope.getScore()) > 0) {
            maxScoreMoveScope = moveScope;
        }
    }

    protected void checkPickEarly(ConstructionHeuristicMoveScope moveScope) {
        switch (pickEarlyType) {
            case NEVER:
                break;
            case FIRST_NON_DETERIORATING_SCORE:
                Score lastStepScore = moveScope.getStepScope().getPhaseScope()
                        .getLastCompletedStepScope().getScore();
                if (moveScope.getScore().compareTo(lastStepScore) >= 0) {
                    earlyPickedMoveScope = moveScope;
                }
                break;
            case FIRST_FEASIBLE_SCORE:
                if (((FeasibilityScore) moveScope.getScore()).isFeasible()) {
                    earlyPickedMoveScope = moveScope;
                }
                break;
            case FIRST_FEASIBLE_SCORE_OR_NON_DETERIORATING_HARD:
                Score lastStepScoreDifference = moveScope.getScore().subtract(moveScope.getStepScope().getPhaseScope()
                        .getLastCompletedStepScope().getScore());
                if (((FeasibilityScore) lastStepScoreDifference).isFeasible()) {
                    earlyPickedMoveScope = moveScope;
                }
                break;
            default:
                throw new IllegalStateException("The pickEarlyType (" + pickEarlyType + ") is not implemented.");
        }
    }

    public boolean isQuitEarly() {
        return earlyPickedMoveScope != null;
    }

    public ConstructionHeuristicMoveScope pickMove(ConstructionHeuristicStepScope stepScope) {
        if (earlyPickedMoveScope != null) {
            return earlyPickedMoveScope;
        } else {
            return maxScoreMoveScope;
        }
    }

}
