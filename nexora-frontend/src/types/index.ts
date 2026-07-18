export interface UserResponse {
  id: number;
  username: string;
  email: string;
}

export interface NoteResponse {
  id: number;
  title: string;
  content: string;
  createdAt: string;
}

export interface ExpenseResponse {
  id: number;
  amount: number;
  category: string;
  description: string;
  date: string;
}