/*
 * Copyright 2002-2005 the original author or authors.
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

package com.vsc.modules.jasperreports.export;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsSingleFormatView;


public class JasperReportsRtfView extends AbstractJasperReportsSingleFormatView {

	public JasperReportsRtfView() {
		setContentType("application/msword");
	}

	@Override
	protected JRExporter createExporter() {
		return new JRRtfExporter();
	}

	@Override
	protected boolean useWriter() {
		return false;
	}

}
