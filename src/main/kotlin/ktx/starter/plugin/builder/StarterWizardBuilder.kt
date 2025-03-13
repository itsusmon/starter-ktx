package ktx.starter.plugin.builder

import com.intellij.ide.wizard.NewProjectWizardStep
import com.intellij.ide.wizard.language.LanguageGeneratorNewProjectWizard
import org.jetbrains.annotations.Nls
import org.jetbrains.kotlin.idea.KotlinIcons
import javax.swing.Icon

class StarterWizardBuilder : LanguageGeneratorNewProjectWizard {

    override val name: String
        @Nls(capitalization = Nls.Capitalization.Title)
        get() = "Starter-KTX"

    override val icon: Icon get() = KotlinIcons.ANNOTATION

    override fun createStep(parent: NewProjectWizardStep): NewProjectWizardStep = Step(parent)

}

