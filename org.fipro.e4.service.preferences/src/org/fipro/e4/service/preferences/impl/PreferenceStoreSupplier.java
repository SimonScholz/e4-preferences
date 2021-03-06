/*******************************************************************************
 * Copyright (c) 2015 Dirk Fauth.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dirk Fauth <dirk.fauth@googlemail.com> - initial API and implementation
 *******************************************************************************/
package org.fipro.e4.service.preferences.impl;

import javax.inject.Inject;

import org.eclipse.e4.core.di.suppliers.ExtendedObjectSupplier;
import org.eclipse.e4.core.di.suppliers.IObjectDescriptor;
import org.eclipse.e4.core.di.suppliers.IRequestor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.fipro.e4.service.preferences.IPreferenceStoreFactoryService;
import org.fipro.e4.service.preferences.PrefStore;

/**
 * {@link ExtendedObjectSupplier} implementation for the {@link PrefStore}
 * annotation. Uses a {@link IPreferenceStoreFactoryService} implementation for
 * providing a {@link IPreferenceStore}.
 */
public class PreferenceStoreSupplier extends ExtendedObjectSupplier {

	@Inject
	private IPreferenceStoreFactoryService service;

	@Override
	public Object get(IObjectDescriptor descriptor, IRequestor requestor, boolean track, boolean group) {
		String qualifier = getKey(descriptor);
		if (qualifier != null) {
			return service.getPreferenceStoreInstance(qualifier);
		}
		return null;
	}

	private String getKey(IObjectDescriptor descriptor) {
		if (descriptor != null) {
			PrefStore qualifier = descriptor.getQualifier(PrefStore.class);
			return qualifier.nodePath();

		}
		return null;
	}
}
