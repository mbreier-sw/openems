package io.openems.edge.core.sum;

import io.openems.edge.common.channel.calculate.CalculateLongSum;
import io.openems.edge.common.component.AbstractOpenemsComponent;
import io.openems.edge.common.component.ComponentManager;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.event.EdgeEventConstants;
import io.openems.edge.common.sum.AggregateRemainingEnergy;
import io.openems.edge.common.sum.Sum;
import io.openems.edge.ess.api.RemainingEssEnergy;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.event.propertytypes.EventTopics;

@Component(//
        name = AggregateRemainingEnergy.SINGLETON_SERVICE_PID, //
        immediate = true, //
        property = { //
                "enabled=true" //
        })
@EventTopics({ //
        EdgeEventConstants.TOPIC_CYCLE_BEFORE_PROCESS_IMAGE, //
})
public class AggregateRemainingEnergyImpl
        extends AbstractOpenemsComponent
        implements AggregateRemainingEnergy, EventHandler, OpenemsComponent {

    @Reference
    private ConfigurationAdmin configurationAdmin;

    @Reference
    private ComponentManager componentManager;

    public AggregateRemainingEnergyImpl() {
        super(//
            OpenemsComponent.ChannelId.values(), //
            AggregateRemainingEnergy.ChannelId.values() //
        );
    }

    @Activate
    private void activate(ComponentContext context, Config config) {
        super.activate(context, SINGLETON_COMPONENT_ID, SINGLETON_SERVICE_PID, true);

        if (OpenemsComponent.validateSingleton(this.configurationAdmin, SINGLETON_SERVICE_PID, SINGLETON_COMPONENT_ID)) {
            return;
        }
    }

    @Modified
    private void modified(ComponentContext context, Config config) {
        super.modified(context, SINGLETON_COMPONENT_ID, SINGLETON_SERVICE_PID, true);

        if (OpenemsComponent.validateSingleton(this.configurationAdmin, SINGLETON_SERVICE_PID, SINGLETON_COMPONENT_ID)) {
            return;
        }
    }

    @Override
    @Deactivate
    protected void deactivate() {
        super.deactivate();
    }

    @Override
    public void handleEvent(Event event) {
        if (!this.isEnabled()) {
            return;
        }
        switch (event.getTopic()) {
            case EdgeEventConstants.TOPIC_CYCLE_BEFORE_PROCESS_IMAGE -> this.processDataOnBeforeProcessImageEvent();
        }
    }

    void processDataOnBeforeProcessImageEvent() {
        this.aggregateRemainingEnergy();
    }

    void aggregateRemainingEnergy() {
        final var sumRemainingChargeCapacity = new CalculateLongSum();
        final var sumRemainingDischargeCapacity = new CalculateLongSum();
        final var sumRemainingAvailableChargePower = new CalculateLongSum();
        final var sumRemainingAvailableDischargePower = new CalculateLongSum();

        // Calculate sums
        for (var remainingEssEnergyComponent : this.componentManager.getEnabledComponentsOfType(RemainingEssEnergy.class)) {
            sumRemainingChargeCapacity.addValue(remainingEssEnergyComponent.getRemainingChargeCapacityChannel());
            sumRemainingDischargeCapacity.addValue(remainingEssEnergyComponent.getRemainingDischargeCapacityChannel());
            sumRemainingAvailableChargePower.addValue(remainingEssEnergyComponent.getRemainingAvailableChargePowerChannel());
            sumRemainingAvailableDischargePower.addValue(remainingEssEnergyComponent.getRemainingAvailableDischargePowerChannel());
        }

        // Set to channels
        this.getSumRemainingChargeCapacityChannel().setNextValue(sumRemainingChargeCapacity.calculate());
        this.getSumRemainingDischargeCapacityChannel().setNextValue(sumRemainingDischargeCapacity.calculate());
        this.getSumRemainingAvailableChargePowerChannel().setNextValue(sumRemainingAvailableChargePower.calculate());
        this.getSumRemainingAvailableDischargePowerChannel().setNextValue(sumRemainingAvailableDischargePower.calculate());
    }
}
