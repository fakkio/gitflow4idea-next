package gitflow.actions;

import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.ex.ActionUtil;
import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.intellij.openapi.project.Project;
import git4idea.actions.GitResolveConflictsAction;

/**
 * All actions associated with Gitflow
 *
 * @author Opher Vishnia / opherv.com / opherv@gmail.com
 */
public class GitflowActions {

    public static void runMergeTool(Project project){
        GitResolveConflictsAction resolveAction = new GitResolveConflictsAction();
        // Just enough context (the project) to let the conflicts resolver launch
        DataContext dataContext = SimpleDataContext.getProjectContext(project);
        // invokeAction is available on every platform version from 2024.2 to 2026.3
        ActionUtil.invokeAction(resolveAction, dataContext, ActionPlaces.UNKNOWN, null, null);
    }
}
