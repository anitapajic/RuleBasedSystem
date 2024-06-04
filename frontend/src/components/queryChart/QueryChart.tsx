/* eslint-disable @typescript-eslint/no-unused-vars */
import { useState, useEffect } from "react";
import { QueryDTO } from "../../models/QueryDTO"
import ReactApexChart from 'react-apexcharts';



export type QueryChartProps = {
    queryData: QueryDTO;
}

export default function QueryChart({ queryData }: QueryChartProps) {
    const [chartData, setChartData] = useState<{ series: number[]; options: ApexCharts.ApexOptions }>({
        series: [queryData.percentage],
        options: getDefaultChartOptions(queryData.numOfPatients.toString(), queryData.percentage)
    });

    useEffect(() => {
        setChartData({
            series: [queryData.percentage],
            options: getDefaultChartOptions(queryData.numOfPatients.toString(), queryData.percentage)
        });
    }, [queryData]);

    function getDefaultChartOptions(totalLabel: string, percentage: number): ApexCharts.ApexOptions {
        return {
            chart: {
                type: 'radialBar' as const,
            },
            plotOptions: {
                radialBar: {
                    dataLabels: {
                        name: {
                            show: true,
                        },
                        value: {
                            show: true,
                            formatter: function () {
                                return percentage.toString();
                            }
                        },
                        total: {
                            show: true,
                            label: 'Patients: ' + totalLabel,
                        }
                    }
                }
            },
            labels: ['Percentage'],
            noData: {
                text: 'Loading...'
            }
        };
    }

    return (
        <>
            <div id="chart">
                <ReactApexChart
                    options={chartData.options}
                    series={chartData.series}
                    type="radialBar"
                    height={450} />
            </div>
        </>
    )
}