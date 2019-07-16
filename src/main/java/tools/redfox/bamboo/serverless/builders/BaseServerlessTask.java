package tools.redfox.bamboo.serverless.builders;

import com.atlassian.bamboo.specs.api.builders.task.Task;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tools.redfox.bamboo.serverless.model.BaseServerlessTaskProperties;

public abstract class BaseServerlessTask<B extends BaseServerlessTask<B, P>, P extends BaseServerlessTaskProperties> extends Task<B, P> {
    @Nullable
    protected String environmentVariables;

    @Nullable
    protected String workingSubdirectory;

    @Nullable
    protected String serverlessExecutable;

    @Nullable
    protected String options;

    /**
     * Sets which Node.js executable to use.
     */
    public B serverlessExecutable(@NotNull String nodeExecutable) {
        this.serverlessExecutable = nodeExecutable;
        return (B) this;
    }

    /**
     * Sets environment variables for this task.
     */
    public B environmentVariables(@Nullable String environmentVariables) {
        this.environmentVariables = environmentVariables;
        return (B) this;
    }

    /**
     * Sets the working subdirectory for this task.
     */
    public B workingSubdirectory(@Nullable String workingSubdirectory) {
        this.workingSubdirectory = workingSubdirectory;
        return (B) this;
    }

    public B options(@Nullable String options) {
        this.options = options;
        return (B) this;
    }
}
