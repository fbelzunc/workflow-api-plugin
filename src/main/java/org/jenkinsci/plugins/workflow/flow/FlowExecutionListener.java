package org.jenkinsci.plugins.workflow.flow;

import hudson.ExtensionList;
import hudson.ExtensionPoint;
import org.jenkinsci.plugins.workflow.graph.FlowEndNode;
import org.jenkinsci.plugins.workflow.graph.FlowNode;

import javax.annotation.Nonnull;

/**
 * Listens for significant status updates for a {@link FlowExecution}, such as started running or completed.
 *
 * @since 2.14
 * @author Andrew Bayer
 */
public abstract class FlowExecutionListener implements ExtensionPoint {

    /**
     * Called when a {@link FlowExecution} has started running.
     *
     * The {@link FlowExecution} will already have been added to the {@link FlowExecutionList} by this point.
     *
     * @param execution The {@link FlowExecution} that has started running.
     */
    public void onRunning(@Nonnull FlowExecution execution) {
    }

    /**
     * Called when a {@link FlowExecution} has resumed.
     *
     * @param execution The {@link FlowExecution} that has resumed.
     */
    public void onResumed(@Nonnull FlowExecution execution) {
    }

    /**
     * Called when a {@link FlowExecution} has completed.
     *
     * The {@link FlowExecution} will already have been removed from the {@link FlowExecutionList} by this point,
     * {@link GraphListener.Synchronous#onNewHead(FlowNode)} will have already been called for the {@link FlowEndNode},
     * {@link FlowExecution#getCurrentHeads()} will have one element, a {@link FlowEndNode}, and if the Pipeline has
     * failed, {@link FlowExecution#getCauseOfFailure()} will return non-null.
     *
     * @param execution The {@link FlowExecution} that has completed.
     */
    public void onCompleted(@Nonnull FlowExecution execution) {
    }

    /**
     * Fires the {@link #onRunning(FlowExecution)} event.
     */
    public static void fireRunning(@Nonnull FlowExecution execution) {
        for (FlowExecutionListener listener : ExtensionList.lookup(FlowExecutionListener.class)) {
            listener.onRunning(execution);
        }
    }

    /**
     * Fires the {@link #onResumed(FlowExecution)} event.
     */
    public static void fireResumed(@Nonnull FlowExecution execution) {
        for (FlowExecutionListener listener : ExtensionList.lookup(FlowExecutionListener.class)) {
            listener.onResumed(execution);
        }
    }

    /**
     * Fires the {@link #onCompleted(FlowExecution)} event.
     */
    public static void fireCompleted(@Nonnull FlowExecution execution) {
        for (FlowExecutionListener listener : ExtensionList.lookup(FlowExecutionListener.class)) {
            listener.onCompleted(execution);
        }
    }
}
