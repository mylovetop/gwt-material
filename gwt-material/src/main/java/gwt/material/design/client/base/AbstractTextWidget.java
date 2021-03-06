/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
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
 * #L%
 */
package gwt.material.design.client.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.ui.client.adapters.HasTextEditor;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.user.client.ui.HasHTML;

/**
 * @author Ben Dol
 */
//TODO : Setting HasRawValue - break the sanitizer
public abstract class AbstractTextWidget extends AbstractValueWidget<String> implements HasId, HasHTML,
        IsEditor<LeafValueEditor<String>> {

    private LeafValueEditor<String> editor;

    protected AbstractTextWidget(Element element) {
        super(element);
    }

    protected AbstractTextWidget(Element element, String... initialClasses) {
        super(element, initialClasses);
    }

    @Override
    public String getValue() {
        return SafeHtmlUtils.fromString(getElement().getInnerText()).asString();
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        value = SafeHtmlUtils.fromString(value).asString();
        getElement().setInnerText(value);
        super.setValue(value, fireEvents);
    }

    @Override
    public void setText(final String text) {
        setValue(text);
    }

    @Override
    public String getText() {
        return getValue();
    }

    @Override
    public String getHTML() {
        return getElement().getInnerHTML();
    }

    @Override
    public void setHTML(final String html) {
        getElement().setInnerSafeHtml(SafeHtmlUtils.fromString(html));
    }

    @Override
    public LeafValueEditor<String> asEditor() {
        if (editor == null) {
            editor = HasTextEditor.of(this);
        }
        return editor;
    }
}
