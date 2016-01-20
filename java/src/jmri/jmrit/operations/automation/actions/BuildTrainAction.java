package jmri.jmrit.operations.automation.actions;

import jmri.jmrit.operations.trains.Train;

public class BuildTrainAction extends Action {

    private static final int _code = ActionCodes.BUILD_TRAIN;

    @Override
    public int getCode() {
        return _code;
    }

    @Override
    public String getName() {
        return Bundle.getMessage("BuildTrain");
    }

    @Override
    public void doAction() {
        if (getAutomationItem() != null) {
            Train train = getAutomationItem().getTrain();
            if (train == null || train.isBuilt()) {
                finishAction(false); // failed to build train
            } else {
                setRunning(true);
                train.build();
                finishAction(true); // okay
            }
        }
    }

    @Override
    public void cancelAction() {
        // no cancel for this action     
    }

}