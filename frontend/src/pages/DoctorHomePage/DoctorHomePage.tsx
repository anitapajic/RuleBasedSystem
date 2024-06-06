import { Container } from "../../components/homeInfo/HomeInfo.styled";
import NewAnamnesisForm from "../../components/newAnamnesisForm/NewAnamnesisForm";

export default function DoctorHomePage(){
    return (
        <div>
            <Container>  
                <NewAnamnesisForm/>    
            </Container>
        </div>
    )
}