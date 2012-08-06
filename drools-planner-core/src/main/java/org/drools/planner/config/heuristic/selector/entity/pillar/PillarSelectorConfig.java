/*
 * Copyright 2012 JBoss Inc
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

package org.drools.planner.config.heuristic.selector.entity.pillar;

import java.util.Collection;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.drools.planner.config.EnvironmentMode;
import org.drools.planner.config.heuristic.selector.SelectorConfig;
import org.drools.planner.config.heuristic.selector.common.SelectionOrder;
import org.drools.planner.config.heuristic.selector.entity.EntitySelectorConfig;
import org.drools.planner.config.heuristic.selector.value.ValueSelectorConfig;
import org.drools.planner.config.util.ConfigUtils;
import org.drools.planner.core.domain.entity.PlanningEntityDescriptor;
import org.drools.planner.core.domain.solution.SolutionDescriptor;
import org.drools.planner.core.domain.variable.PlanningVariableDescriptor;
import org.drools.planner.core.heuristic.selector.common.SelectionCacheType;
import org.drools.planner.core.heuristic.selector.entity.EntitySelector;
import org.drools.planner.core.heuristic.selector.entity.pillar.PillarSelector;
import org.drools.planner.core.heuristic.selector.entity.pillar.SameValuePillarSelector;
import org.drools.planner.core.heuristic.selector.value.ValueSelector;
import org.drools.planner.core.heuristic.selector.value.chained.DefaultSubChainSelector;
import org.drools.planner.core.heuristic.selector.value.chained.SubChainSelector;

@XStreamAlias("pillarSelector")
public class PillarSelectorConfig extends SelectorConfig {

    @XStreamAlias("entitySelector")
    private EntitySelectorConfig entitySelectorConfig = new EntitySelectorConfig();

    // TODO add planningVariableName but do not duplicate from PillarSwapMoveSelectorConfig
//    @XStreamImplicit(itemFieldName = "planningVariableName")
//    private List<String> planningVariableNameList = null;

    public EntitySelectorConfig getEntitySelectorConfig() {
        return entitySelectorConfig;
    }

    public void setEntitySelectorConfig(EntitySelectorConfig entitySelectorConfig) {
        this.entitySelectorConfig = entitySelectorConfig;
    }

//    public List<String> getPlanningVariableNameList() {
//        return planningVariableNameList;
//    }
//
//    public void setPlanningVariableNameList(List<String> planningVariableNameList) {
//        this.planningVariableNameList = planningVariableNameList;
//    }

    // ************************************************************************
    // Builder methods
    // ************************************************************************

    /**
     * @param environmentMode never null
     * @param solutionDescriptor never null
     * @param inheritedSelectionOrder never null
     * @param minimumCacheType never null, If caching is used (different from {@link SelectionCacheType#JUST_IN_TIME}),
     * then it should be at least this {@link SelectionCacheType} because an ancestor already uses such caching
     * and less would be pointless.
     * @return never null
     */
    public PillarSelector buildPillarSelector(EnvironmentMode environmentMode, SolutionDescriptor solutionDescriptor,
            SelectionOrder inheritedSelectionOrder, SelectionCacheType minimumCacheType) {
        if (minimumCacheType.compareTo(SelectionCacheType.STEP) > 0) {
            throw new IllegalArgumentException("The subChainChangeMoveSelector's minimumCacheType (" + minimumCacheType
                    + ") must not be higher than " + SelectionCacheType.STEP
                    + " because the pillars change every step.");
        }
        // EntitySelector uses SelectionOrder.ORIGINAL because a SameValuePillarSelector STEP caches the values
        EntitySelector entitySelector = entitySelectorConfig.buildEntitySelector(environmentMode, solutionDescriptor,
                SelectionOrder.ORIGINAL, minimumCacheType);
        Collection<PlanningVariableDescriptor> variableDescriptors = entitySelector.getEntityDescriptor()
                .getPlanningVariableDescriptors();
        return new SameValuePillarSelector(entitySelector, variableDescriptors,
                inheritedSelectionOrder == SelectionOrder.RANDOM);
    }

    public void inherit(PillarSelectorConfig inheritedConfig) {
        super.inherit(inheritedConfig);
        if (entitySelectorConfig == null) {
            entitySelectorConfig = inheritedConfig.getEntitySelectorConfig();
        } else if (inheritedConfig.getEntitySelectorConfig() != null) {
            entitySelectorConfig.inherit(inheritedConfig.getEntitySelectorConfig());
        }
//        planningVariableNameList = ConfigUtils.inheritMergeableListProperty(planningVariableNameList,
//                inheritedConfig.getPlanningVariableNameList());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + entitySelectorConfig + ")";
    }

}