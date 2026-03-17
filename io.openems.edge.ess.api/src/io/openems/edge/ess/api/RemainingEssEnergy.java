package io.openems.edge.ess.api;

import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.channel.LongDoc;
import io.openems.edge.common.channel.LongReadChannel;
import io.openems.edge.common.channel.value.Value;

import static io.openems.common.channel.PersistencePriority.HIGH;
import static io.openems.common.channel.Unit.WATT;
import static io.openems.common.channel.Unit.WATT_HOURS;

public interface RemainingEssEnergy extends ManagedSymmetricEss {

    public enum ChannelId implements io.openems.edge.common.channel.ChannelId {

        REMAINING_CHARGE_CAPACITY(new LongDoc()//
                .unit(WATT_HOURS)//
                .persistencePriority(HIGH)), //

        REMAINING_DISCHARGE_CAPACITY(new LongDoc()//
                .unit(WATT_HOURS)//
                .persistencePriority(HIGH)), //

        REMAINING_AVAILABLE_CHARGE_POWER(new LongDoc()//
                .unit(WATT)//
                .persistencePriority(HIGH)), //

        REMAINING_AVAILABLE_DISCHARGE_POWER(new LongDoc()//
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

    /**
     * Gets the Channel for {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     * @return the Channel
     */
    public default LongReadChannel getRemainingChargeCapacityChannel() {
        return this.channel(ChannelId.REMAINING_CHARGE_CAPACITY);
    }

    /**
     * Gets the Allowed Charge Power in [W], range "<= 0". See
     * {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     * @return the Channel {@link Value}
     */
    public default Value<Long> getRemainingAvailableChargeCapacity() {
        return this.getRemainingChargeCapacityChannel().value();
    }

    /**
     * Gets the Allowed Charge Power in [W], range "<= 0". See
     * {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     */
    public default void _setRemainingAvailableChargeCapacity(Long value) {
        this.getRemainingChargeCapacityChannel().setNextValue(value);
    }

    /**
     * Gets the Channel for {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     * @return the Channel
     */
    public default LongReadChannel getRemainingDischargeCapacityChannel() {
        return this.channel(ChannelId.REMAINING_DISCHARGE_CAPACITY);
    }

    /**
     * Gets the Allowed Charge Power in [W], range "<= 0". See
     * {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     * @return the Channel {@link Value}
     */
    public default Value<Long> getRemainingAvailableDischargeCapacity() {
        return this.getRemainingDischargeCapacityChannel().value();
    }

    /**
     * Gets the Allowed Charge Power in [W], range "<= 0". See
     * {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     */
    public default void _setRemainingAvailableDischargeCapacity(Long value) {
        this.getRemainingDischargeCapacityChannel().setNextValue(value);
    }

    /**
     * Gets the Channel for {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     * @return the Channel
     */
    public default LongReadChannel getRemainingAvailableChargePowerChannel() {
        return this.channel(ChannelId.REMAINING_AVAILABLE_CHARGE_POWER);
    }

    /**
     * Gets the Allowed Charge Power in [W], range "<= 0". See
     * {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     * @return the Channel {@link Value}
     */
    public default Value<Long> getRemainingAvailableChargePower() {
        return this.getRemainingAvailableChargePowerChannel().value();
    }

    /**
     * Gets the Allowed Charge Power in [W], range "<= 0". See
     * {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     */
    public default void _setRemainingAvailableChargePower(Long value) {
        this.getRemainingAvailableChargePowerChannel().setNextValue(value);
    }

    /**
     * Gets the Channel for {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     * @return the Channel
     */
    public default LongReadChannel getRemainingAvailableDischargePowerChannel() {
        return this.channel(ChannelId.REMAINING_AVAILABLE_DISCHARGE_POWER);
    }

    /**
     * Gets the Allowed Charge Power in [W], range "<= 0". See
     * {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     * @return the Channel {@link Value}
     */
    public default Value<Long> getRemainingAvailableDischargePower() {
        return this.getRemainingAvailableDischargePowerChannel().value();
    }

    /**
     * Gets the Allowed Charge Power in [W], range "<= 0". See
     * {@link ManagedSymmetricEss.ChannelId#ALLOWED_CHARGE_POWER}.
     *
     */
    public default void _setRemainingAvailableDischargePower(Long value) {
        this.getRemainingAvailableDischargePowerChannel().setNextValue(value);
    }


}
