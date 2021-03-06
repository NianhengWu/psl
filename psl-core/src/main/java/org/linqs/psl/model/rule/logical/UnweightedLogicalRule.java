/*
 * This file is part of the PSL software.
 * Copyright 2011-2015 University of Maryland
 * Copyright 2013-2018 The Regents of the University of California
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.linqs.psl.model.rule.logical;

import java.util.List;

import org.linqs.psl.model.atom.GroundAtom;
import org.linqs.psl.model.formula.Formula;
import org.linqs.psl.model.rule.Rule;
import org.linqs.psl.model.rule.UnweightedRule;

public class UnweightedLogicalRule extends AbstractLogicalRule implements UnweightedRule {
	public UnweightedLogicalRule(Formula f) {
		this(f, f.toString());
	}

	public UnweightedLogicalRule(Formula f, String name) {
		super(f, name);
	}

	@Override
	protected AbstractGroundLogicalRule groundFormulaInstance(List<GroundAtom> posLiterals, List<GroundAtom> negLiterals, int rvaCount) {
		return new UnweightedGroundLogicalRule(this, posLiterals, negLiterals, rvaCount);
	}

	@Override
	public String toString() {
		return formula.toString() + " .";
	}

	@Override
	public boolean isWeighted() {
		return false;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || this.getClass() != other.getClass()) {
			return false;
		}

		return super.equals(other);
	}
}
