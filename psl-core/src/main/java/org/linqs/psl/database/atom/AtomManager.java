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
package org.linqs.psl.database.atom;

import org.linqs.psl.database.Database;
import org.linqs.psl.database.DatabaseQuery;
import org.linqs.psl.database.ResultList;
import org.linqs.psl.model.atom.GroundAtom;
import org.linqs.psl.model.formula.Formula;
import org.linqs.psl.model.predicate.Predicate;
import org.linqs.psl.model.predicate.StandardPredicate;
import org.linqs.psl.model.term.Constant;

/**
 * Provides centralization and hooks for managing the {@link GroundAtom GroundAtoms}
 * that are instantiated from a {@link Database}.
 *
 * By wrapping {@link Database#getAtom(Predicate, Constant...)},
 * an AtomManager gives additional control over the GroundAtoms that come from
 * that Database.
 *
 * Additionally, AtomManagers can support other functionality that might require
 * coordination by providing a single component to call to carry out tasks.
 *
 * An AtomManager should be initialized with the Database for which it is managing
 * Atoms.
 */
public abstract class AtomManager {
	protected final Database db;

	public AtomManager(Database db) {
		this.db = db;
	}

	/**
	 * Returns the GroundAtom for the given Predicate and GroundTerms.
	 *
	 * This method must call {@link Database#getAtom(Predicate, Constant...)}
	 * to actually retrieve the GroundAtom.
	 *
	 * @param predicate the Predicate of the Atom
	 * @param arguments the GroundTerms of the Atom
	 * @return the Atom
	 */
	public abstract GroundAtom getAtom(Predicate predicate, Constant... arguments);

	/**
	 * Calls {@link Database#executeQuery(DatabaseQuery)} on the
	 * encapsulated Database.
	 *
	 * @param query the query to execute
	 * @return the query results exactly as returned by the Database
	 */
	public ResultList executeQuery(DatabaseQuery query) {
		return db.executeQuery(query);
	}

	/**
	 * Calls {@link Database#executeGroundingQuery(Formula)} on the
	 * encapsulated Database.
	 */
	public ResultList executeGroundingQuery(Formula formula) {
		return db.executeGroundingQuery(formula);
	}

	/**
	 * Calls {@link Database#isClosed(StandardPredicate)} on the
	 * encapsulated Database.
	 *
	 * @param predicate the predicate to check
	 * @return TRUE if predicate is closed in the Database
	 */
	public boolean isClosed(StandardPredicate predicate) {
		return db.isClosed(predicate);
	}

	public Database getDatabase() {
		return db;
	}
}
