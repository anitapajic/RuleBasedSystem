import { Container, Image, ImageSection, TextContainer, TextDescription, TextHeadLine, TextStats, TextStatsContainer, TextStatsStyle, TextStatsStyleBold, TextTitle } from "./HomeInfo.styled";
import Doctor from "../../assets/doctor-picture.png";
export default function HomeInfo(){
    return (
        <>
        <div className="section-container">
            <Container>
                <TextContainer>
                    <TextHeadLine>❤️ Health comes first</TextHeadLine>
                    <TextTitle>Find your Doctor and make an Appointments</TextTitle>
                    <TextDescription>
                        Talk to online doctors and get medical advice, online prescriptions,
                        refills and medical notes within minutes. On-demand healthcare
                        services at your fingertips.
                    </TextDescription>
                    <TextStats>
                        <TextStatsContainer>
                            <TextStatsStyleBold>145k+</TextStatsStyleBold>
                            <TextStatsStyle>Receive Patients</TextStatsStyle>
                        </TextStatsContainer>
                        <TextStatsContainer>
                            <TextStatsStyleBold>50+</TextStatsStyleBold>
                            <TextStatsStyle>Expert Doctors</TextStatsStyle>
                        </TextStatsContainer>
                        <TextStatsContainer>
                            <TextStatsStyleBold>10+</TextStatsStyleBold>
                            <TextStatsStyle>Years of Experience</TextStatsStyle>
                        </TextStatsContainer>
                    </TextStats>
                </TextContainer>
                <ImageSection>
                    <Image src={Doctor} alt="Doctor"/>
                </ImageSection>
            </Container>
            </div>
        </>
    )
}