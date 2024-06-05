import {ScrollableContainer, StyledTable, TableWrapper} from "../patientsTable/PatientsTable.styled.tsx";
import {PatientMonitoring} from "../../models/Patient.ts";
import {formatDate} from "../../utils/functions/formatDateTime.ts";

type MonitoringTableProps = {
    patientMonitoring: PatientMonitoring[],
    monitoringType: string
}

export default function MonitoringTable({ monitoringType, patientMonitoring } : MonitoringTableProps) {
    return (
        <TableWrapper>
            <ScrollableContainer>
                <StyledTable>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Email</th>
                            <th>Date of birth</th>
                            <th>Weight</th>
                            {monitoringType == "temperature" && <th>Temperature</th>}
                            {monitoringType == "oxygen" && <th>Oxygen</th>}
                        </tr>
                    </thead>
                    {patientMonitoring.length > 0 ? (
                        patientMonitoring.map(patient => (
                            <tr key={patient.id}>
                                <td>{patient.name}</td>
                                <td>{patient.surname}</td>
                                <td>{patient.email}</td>
                                <td>{formatDate(patient.birthDate)}</td>
                                <td>{patient.weight.toString()}kg</td>
                                <td>{patient.monitoringValue.toString()}
                                    {monitoringType == "temperature" ? "℃" : "%"}</td>

                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan={5}><h2>No data</h2></td>
                        </tr>
                    )}

                </StyledTable>
            </ScrollableContainer>
        </TableWrapper>
    )
}