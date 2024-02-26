package statusmachinetest;

import common.DemoEvent;
import common.DemoStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class StatusMachineTest {

    @Test
    void should_transit_to_next_status_success_case_1() {
        // 配置
        DemoStatusMachine statusMachine = new DemoStatusMachine();
        DemoStatus currentStatus = DemoStatus.NEW;

        // 执行测试的业务逻辑
        DemoStatus nextStatus = statusMachine.getNextStatus(DemoEvent.SUBMIT, currentStatus);

        // 验证结果
        assertEquals(DemoStatus.SUBMITTED, nextStatus);
    }

    @Test
    void should_transit_to_next_status_success_case_2() {
        // 配置
        DemoStatusMachine statusMachine = new DemoStatusMachine();
        DemoStatus currentStatus = DemoStatus.UPDATED;

        // 执行测试的业务逻辑
        DemoStatus nextStatus = statusMachine.getNextStatus(DemoEvent.SUBMIT, currentStatus);

        // 验证结果
        assertEquals(DemoStatus.SUBMITTED, nextStatus);
    }

    @Test
    void should_throw_error_if_status_invalid() {
        // 配置
        DemoStatusMachine statusMachine = new DemoStatusMachine();
        DemoStatus currentStatus = DemoStatus.SUBMITTED;

        // 执行测试的业务逻辑
        assertThrows(IllegalArgumentException.class,
                () -> statusMachine.getNextStatus(DemoEvent.SUBMIT, currentStatus)
        );
    }

    @Test
    void should_return_true_if_last_status() {
        // 配置
        DemoStatusMachine statusMachine = new DemoStatusMachine();
        DemoStatus lastStatus = DemoStatus.APPROVED;

        // 执行测试的业务逻辑
        boolean isFinal = statusMachine.isFinalStatus(lastStatus);

        // 验证结果
        assertTrue(isFinal);
    }

    @Test
    void should_return_false_if_not_last_status() {
        // 配置
        DemoStatusMachine statusMachine = new DemoStatusMachine();
        DemoStatus lastStatus = DemoStatus.CALCULATED;

        // 执行测试的业务逻辑
        boolean isFinal = statusMachine.isFinalStatus(lastStatus);

        // 验证结果
        assertFalse(isFinal);
    }

}
