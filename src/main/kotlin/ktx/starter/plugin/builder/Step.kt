package ktx.starter.plugin.builder

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.ide.wizard.NewProjectWizardStep.Keywords
import com.intellij.openapi.observable.properties.PropertyGraph
import com.intellij.openapi.util.UserDataHolder
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.ui.dsl.builder.Panel

class Step(parent: NewProjectWizardStep) : NewProjectWizardStep {

    override val context: WizardContext = parent.context

    override val data: UserDataHolder = UserDataHolderBase()

    override val keywords: Keywords = Keywords()

    override val propertyGraph: PropertyGraph = PropertyGraph()

    private var artifactId by propertyGraph.property("")

    override fun setupUI(builder: Panel) {
        builder.row {
            val textField = textField()
            textField.onChanged {
                artifactId = it.text
            }
        }
    }
}