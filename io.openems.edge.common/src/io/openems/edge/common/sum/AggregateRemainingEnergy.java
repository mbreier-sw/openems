package io.openems.edge.common.sum;

import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.channel.LongDoc;
import io.openems.edge.common.channel.LongReadChannel;
import io.openems.edge.common.channel.value.Value;
import io.openems.edge.common.component.OpenemsComponent;

import static io.openems.common.channel.PersistencePriority.HIGH;
import static io.openems.common.channel.Unit.WATT;
import static io.openems.common.channel.Unit.WATT_HOURS;

public interface AggregateRemainingEnergy  extends OpenemsComponent {

	public static final String SINGLETON_SERVICE_PID = "Core.AggregateRemainingEnergy";
    public static final String SINGLETON_COMPONENT_ID = "_aggregateRemainingEnergy";

    public enum ChannelId implements io.openems.edge.common.channel.ChannelId {

        SUM_REMAINING_CHARGE_CAPACITY(new LongDoc()//
                .unit(WATT_HOURS)//
                .persistencePriority(HIGH)), //

        SUM_REMAINING_DISCHARGE_CAPACITY(new LongDoc()//
                .unit(WATT_HOURS)//
                .persistencePriority(HIGH)), //

        SUM_REMAINING_AVAILABLE_CHARGE_POWER(new LongDoc()//
                .unit(WATT)//
                .persistencePriority(HIGH)), //

        SUM_REMAINING_AVAILABLE_DISCHARGE_POWER(new LongDoc()//
                .unit(WATT)//
                .persistencePriority(HIGH)), //
        ;

        private final Doc doc;

        private ChannelId(Doc doc) {
            this.doc = doc;
        }

        @Override
        public Doc doc() {
            return this.doc;
        }
    }

    public default LongReadChannel getSumRemainingChargeCapacityChannel() {
        return this.channel(ChannelId.SUM_REMAINING_CHARGE_CAPACITY);
    }

    public default LongReadChannel getSumRemainingDischargeCapacityChannel() {
        return this.channel(ChannelId.SUM_REMAINING_DISCHARGE_CAPACITY);
    }

    public default LongReadChannel getSumRemainingAvailableChargePowerChannel() {
        return this.channel(ChannelId.SUM_REMAINING_AVAILABLE_CHARGE_POWER);
    }

    public default LongReadChannel getSumRemainingAvailableDischargePowerChannel() {
        return this.channel(ChannelId.SUM_REMAINING_AVAILABLE_DISCHARGE_POWER);
    }

    public default Value<Long> getSumRemainingAvailableChargePower() {
        return this.getSumRemainingAvailableChargePowerChannel().value();
    }

    public default Value<Long> getSumRemainingAvailableDischargePower() {
        return this.getSumRemainingAvailableDischargePowerChannel().value();
    }

    public default Value<Long> getSumRemainingChargeCapacity() {
        return this.getSumRemainingChargeCapacityChannel().value();
    }

    public default Value<Long> getSumRemainingDischargeCapacity() {
        return this.getSumRemainingDischargeCapacityChannel().value();
    }
}
